"""
Example Python gRPC client
"""

import grpc
import my_grpc.service_pb2 as pb
import my_grpc.service_pb2_grpc as pb_grpc
import uuid

channel = grpc.insecure_channel('localhost:50051')
stub = pb_grpc.CircleStub(channel)

req = pb.CreateWalletSetRequest(walletName="wfr3", idempotencyKey=str(uuid.uuid4()))
resp = stub.CreateWalletSet(req)