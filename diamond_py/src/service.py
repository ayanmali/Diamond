"""
gRPC server for Circle API POST requests (related to wallets and wallet sets)
"""
from concurrent import futures
from typing import List
from dotenv import load_dotenv
import os
from my_grpc import service_pb2 as pb
from my_grpc import service_pb2_grpc as pb_grpc
import grpc
from circle.web3 import utils
from circle.web3 import developer_controlled_wallets

load_dotenv()

class CircleServicer(pb_grpc.CircleServicer):
    def __init__(self):
        super().__init__()
        # result = utils.register_entity_secret_ciphertext(api_key=os.getenv("CIRCLE_API_KEY"), entity_secret=os.getenv("CIRCLE_ENTITY_SECRET"))
        # print(result)

        self.client = utils.init_developer_controlled_wallets_client(
	        api_key=os.getenv("CIRCLE_API_KEY"),
	        entity_secret=os.getenv("CIRCLE_ENTITY_SECRET")
        )
        self.ws_api_instance = developer_controlled_wallets.WalletSetsApi(self.client)
        self.wallet_api_instance = developer_controlled_wallets.WalletsApi(self.client)


    def CreateWalletSet(self, request: pb.CreateWalletSetRequest, context) -> pb.CreateWalletSetResponse:
        try:
            # Extract values from the RPC parameters
            wallet_name = request.walletName if request.HasField('walletName') else ""
            #idempotency_key = request.idempotencyKey
            
            # Forming the request
            circle_request = developer_controlled_wallets.CreateWalletSetRequest.from_dict({
                "name": wallet_name
            })

            # Sending the request and getting the response
            response = self.ws_api_instance.create_wallet_set(circle_request)
            print(f"Response: {response}")
            
            # Create and return the response to the caller
            return pb.CreateWalletSetResponse(id = response.data.wallet_set.actual_instance.id)
        
        except developer_controlled_wallets.ApiException as e:
            print("Exception when calling WalletSetsApi->create_wallet_set: %s\n" % e)

        except Exception as e:
            context.set_code(grpc.StatusCode.INTERNAL)
            context.set_details(f'Error creating wallet set: {str(e)}')
    
    def CreateWallet(self, request: pb.CreateWalletRequest, context) -> pb.CreateWalletResponse:
        try:
            # Extracting data from the RPC parameters
            chain: str = request.blockchain
            wallet_set_id: str = request.walletSetId
            idempotency_key: str = request.idempotencyKey

            # Forming the request object
            circle_request = developer_controlled_wallets.CreateWalletRequest.from_dict({
                "accountType" : "EOA",
                "blockchains" : [chain],
                "count" : 1,
                "walletSetId" : wallet_set_id,
                "idempotencyKey" : idempotency_key
            })

            # Sending the request and getting the response
            response = self.wallet_api_instance.create_wallet(circle_request)
            print(f"Response: {response}")

            # Returning the desired data
            data = response.data.wallets[0]
            return pb.CreateWalletResponse(
                id = data.id,
                blockchain = data.blockchains[0],
                address = data.address,
                createDate = data.createDate,
                custodyType = data.custodyType,
                name = data.name,
                walletSetId = data.walletSetId,
                initialPublicKey = data.initialPublicKey
            )

        # Error handling
        except developer_controlled_wallets.ApiException as e:
            print("Exception when calling WalletsApi->create_wallets: %s\n" % e)

        except Exception as e:
            context.set_code(grpc.StatusCode.INTERNAL)
            context.set_details(f'Error creating wallet: {str(e)}')
            
    def CreateWallets(self, request: pb.CreateWalletsRequest, context) -> pb.CreateWalletsResponse:
        try:
             # Extracting data from the RPC parameters
            count: int = request.count
            blockchains: List[str] = request.blockchains
            wallet_set_id: str = request.walletSetId
            idempotency_key: str = request.idempotencyKey

            # Forming the request
            circle_request = developer_controlled_wallets.CreateWalletRequest.from_dict({
                "count": count,
                "blockchains": blockchains,
                "walletSetId": wallet_set_id,
                "idempotencyKey": idempotency_key
            })

            # Sending the request and getting the response
            response = self.wallet_api_instance.create_wallet(circle_request)
            print(f"Response: {response}")

            # Returning the data to the caller
            data = response.data.wallets
            wallets: List[pb.CreateWalletResponse] = []
            for wallet in data:
                wallets.append(
                    pb.CreateWalletResponse(
                    id = wallet.id,
                    blockchain = wallet.blockchains[0],
                    address = wallet.address,
                    createDate = wallet.createDate,
                    custodyType = wallet.custodyType,
                    name = wallet.name,
                    walletSetId = wallet.walletSetId,
                    initialPublicKey = wallet.initialPublicKey
                    )
                )
            return pb.CreateWalletsResponse(wallets)  

        # Error handling
        except developer_controlled_wallets.ApiException as e:
            print("Exception when calling WalletsApi->create_wallets: %s\n" % e)

        except Exception as e:
            context.set_code(grpc.StatusCode.INTERNAL)
            context.set_details(f'Error creating wallet: {str(e)}')

def serve():
    # Create a gRPC server
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    
    # Add the servicer to the server
    pb_grpc.add_CircleServicer_to_server(
        CircleServicer(), server
    )
    
    # Start the server
    # TODO: add secure port https://grpc.io/docs/languages/python/alts/
    server.add_insecure_port('[::]:50051')  # Replace with your desired port
    server.start()
    print("Server started on port 50051")
    
    # Keep the server running
    server.wait_for_termination()

if __name__ == '__main__':
    serve()
