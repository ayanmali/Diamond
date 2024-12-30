package main

import (
	"context"

	"github.com/ayanmali/diamond/go-service/proto"
)

type GRPCSwapperServer struct {
	service StablecoinSwapperService
}

// Constructor method for the gRPC server struct
func NewGRPCSwapperServer(service StablecoinSwapperService) *GRPCSwapperServer {
	return &GRPCSwapperServer{
		service: service,
	}
}

// The method for the server to make the remote procedure call
func (s *GRPCSwapperServer) SwapStablesToSpot(ctxt context.Context, request *proto.SwapSpotRequest) (*proto.SwapSpotResponse, error) {

}

func (s *GRPCSwapperServer) SwapStablesToFiat() {

}
