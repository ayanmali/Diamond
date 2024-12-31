/*
 * Defining the server to allow the gRPC swapper service to execute
 */
package main

import (
	"context"
	"time"

	"github.com/ayanmali/Diamond/diamond-go-service/proto"
)

type GRPCSwapperServer struct {
	service SwapperService
}

// Constructor method for the gRPC server struct
func NewGRPCSwapperServer(service SwapperService) *GRPCSwapperServer {
	return &GRPCSwapperServer{
		service: service,
	}
}

func convertPbVendorWallet() {

}

func (s *GRPCSwapperServer) SwapStablesToSpot(ctxt context.Context, request *proto.SwapStablesToSpotRequest) (*proto.SwapStablesToSpotResponse, error) {
	// Using data from `request` parameter to interact w/ the exchange and perform the swap
	address := request.VendorWallet.GetWalletAddress()
	vendorId := uint64(request.VendorWallet.GetVendorID())
	chain, ok := getChainFromString(request.VendorWallet.GetChain())
	if !ok {
		panic("Chain name not found")
	}

	s.service.SwapStablesToSpot(ctxt,
		*NewVendorWalletFromPb(),
		request.InitialCurrency,
		float64(request.Amount),
		request.TargetToken)
	time.Sleep(2 * time.Second)
	return &proto.SwapStablesToSpotResponse{Response: "Swapped stablecoin to spot token"}, nil
}

func (s *GRPCSwapperServer) SwapStablesToFiat(ctxt context.Context, request *proto.SwapStablesToFiatRequest) (*proto.SwapStablesToFiatResponse, error) {
	// Using data from `request` parameter to interact w/ the exchange and perform the swap
	time.Sleep(2 * time.Second)
	return &proto.SwapStablesToFiatResponse{Response: "Swapped stablecoin to fiat currency"}, nil
}

func (s *GRPCSwapperServer) SwapSpotToFiat(ctxt context.Context, request *proto.SwapSpotToFiatRequest) (*proto.SwapSpotToFiatResponse, error) {
	// Using data from `request` parameter to interact w/ the exchange and perform the swap
	time.Sleep(2 * time.Second)
	return &proto.SwapSpotToFiatResponse{Response: "Swapped spot token to fiat currency"}, nil
}
