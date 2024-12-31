/*
 * Defining the server to allow the gRPC swapper service to execute
 */
package main

import (
	"context"
	"fmt"
	"log"
	"net"

	"github.com/ayanmali/Diamond/diamond-go-service/proto"
	"google.golang.org/grpc"
)

type GRPCSwapperServer struct {
	service                        SwapperService
	proto.UnimplementedSwapsServer // struct embedding
}

// Constructor method for the gRPC server struct
func NewGRPCSwapperServer(service SwapperService) *GRPCSwapperServer {
	return &GRPCSwapperServer{
		service: service,
	}
}

func (s *GRPCSwapperServer) SwapStablesToSpot(ctxt context.Context, request *proto.SwapStablesToSpotRequest) (*proto.SwapStablesToSpotResponse, error) {
	// Grabbing the data from the request to interact w/ the exchange and perform the swap
	// Formatting the data passed over gRPC into the structs/types used by the service
	// Vendor wallet formatting
	address := request.VendorWallet.GetWalletAddress()
	vendorId := uint64(request.VendorWallet.GetVendorID())
	chain, ok := getChainFromString(request.VendorWallet.GetChain())
	if !ok {
		panic("Chain name not found")
	}
	// stablecoin currency formatting
	currency, ok := getStablecoinFromString(request.InitialCurrency)
	if !ok {
		panic("Stablecoin currency not found")
	}
	// Blockchain/spot token ticker formatting
	targetToken, ok := getChainFromString(request.TargetToken)
	if !ok {
		panic("Blockchain (spot token ticker value) not found")
	}

	// Calling our service (business logic)
	s.service.SwapStablesToSpot(ctxt,
		*NewVendorWalletFromPb(address, vendorId, chain),
		currency,
		float64(request.Amount),
		targetToken)

	// time.Sleep(2 * time.Second) // to be removed once API calls to the exchange are implemented
	return &proto.SwapStablesToSpotResponse{Response: "Swapped stablecoin to spot token"}, nil
}

func (s *GRPCSwapperServer) SwapStablesToFiat(ctxt context.Context, request *proto.SwapStablesToFiatRequest) (*proto.SwapStablesToFiatResponse, error) {
	// Using data from `request` parameter to interact w/ the exchange and perform the swap
	// Formatting the data passed over gRPC into the structs/types used by the service
	address := request.VendorWallet.GetWalletAddress()
	vendorId := uint64(request.VendorWallet.GetVendorID())
	chain, ok := getChainFromString(request.VendorWallet.GetChain())
	if !ok {
		panic("Chain name not found")
	}

	// stablecoin currency formatting
	currency, ok := getStablecoinFromString(request.GetCurrency())
	if !ok {
		panic("Stablecoin currency not found")
	}

	// Calling our service (business logic)
	s.service.SwapStablesToFiat(ctxt,
		*NewVendorWalletFromPb(address, vendorId, chain),
		currency,
		float64(request.GetAmount()))

	// time.Sleep(2 * time.Second)
	return &proto.SwapStablesToFiatResponse{Response: "Swapped stablecoin to fiat currency"}, nil
}

func (s *GRPCSwapperServer) SwapSpotToFiat(ctxt context.Context, request *proto.SwapSpotToFiatRequest) (*proto.SwapSpotToFiatResponse, error) {
	// Using data from `request` parameter to interact w/ the exchange and perform the swap
	// Formatting the data passed over gRPC into the structs/types used by the service
	address := request.VendorWallet.GetWalletAddress()
	vendorId := uint64(request.VendorWallet.GetVendorID())
	chain, ok := getChainFromString(request.VendorWallet.GetChain())
	if !ok {
		panic("Chain name not found")
	}

	// Calling our service (business logic)
	s.service.SwapSpotToFiat(ctxt,
		*NewVendorWalletFromPb(address, vendorId, chain),
		float64(request.GetAmount()))
	// time.Sleep(2 * time.Second)
	return &proto.SwapSpotToFiatResponse{Response: "Swapped spot token to fiat currency"}, nil
}

func RunGRPCServer(service SwapperService, port int) error {
	// Specify the port to listen for client requests
	// port := 5000

	grpcSwapperServer := NewGRPCSwapperServer(service)

	lis, err := net.Listen("tcp", fmt.Sprintf("localhost:%d", port))
	if err != nil {
		log.Fatalf("failed to listen on port %d: %v\n", port, err)
		return err
	}

	var opts []grpc.ServerOption
	// Instantiate the gRPC server
	grpcServer := grpc.NewServer(opts...)
	// Register the service implementation w/ the gRPC server
	proto.RegisterSwapsServer(grpcServer, grpcSwapperServer)
	// Serve incoming requests
	grpcServer.Serve(lis)

	return nil
}
