from concurrent import futures
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
        self.api_instance = developer_controlled_wallets.WalletSetsApi(self.client)

    def CreateWalletSet(self, request: pb.CreateWalletSetRequest, context) -> pb.CreateWalletSetResponse:
        try:
            # Extract values from the request
            wallet_name = request.walletName if request.HasField('walletName') else ""
            idempotency_key = request.idempotencyKey
            
            request = developer_controlled_wallets.CreateWalletSetRequest.from_dict({
                "name": "my_wallet_set"
            })
            response = self.api_instance.create_wallet_set(request)
            print(f"Response: {response}")
            
            # Create and return the response
            return pb.CreateWalletSetResponse(id = response.data.wallet_set.actual_instance.id)
        
        except developer_controlled_wallets.ApiException as e:
            print("Exception when calling WalletSetsApi->create_wallet_set: %s\n" % e)

        except Exception as e:
            context.set_code(grpc.StatusCode.INTERNAL)
            context.set_details(f'Error creating wallet set: {str(e)}')
            raise

def serve():
    # Create a gRPC server
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    
    # Add the servicer to the server
    pb_grpc.add_CircleServicer_to_server(
        CircleServicer(), server
    )
    
    # Start the server
    server.add_insecure_port('[::]:50051')  # Replace with your desired port
    server.start()
    print("Server started on port 50051")
    
    # Keep the server running
    server.wait_for_termination()

if __name__ == '__main__':
    serve()
