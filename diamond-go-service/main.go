package main

// func HomeRouteFunc(w http.ResponseWriter, r *http.Request) {
// 	w.Write([]byte("Welcome"))
// 	w.Header().Add("Content-Type", "application/json")
// }

// func main() {
// const url string = "https://www.google.com"
// service := NewSwapperService(url)
// service = NewLoggingService(service)

// var myWallet AccountWallet = *NewAccountWallet("0x000", "myWallet", 0, BASE)

// err := service.SwapStablesToSpot(context.TODO(), myWallet, USDC, 1.0, BASE)

// if err != nil {
// 	log.Fatal(err)
// }

//fmt.Printf("Swapped %f %v to %v successfully\n")

// defining the port number
// var port int = 8000
// r := mux.NewRouter()

// r.HandleFunc("/", HomeRouteFunc).Methods(http.MethodGet)

// http.Handle("", r)

// Logging and error handling
// fmt.Printf("Listening on port %d\n", port)
// err := http.ListenAndServe(fmt.Sprintf(":%d", port), nil)
// if err != nil {
// 	fmt.Errorf("Error when listening on port %d\n", port)
// }
// }

func main() {
	// // Specify the port to listen for client requests
	// port := 5000
	// lis, err := net.Listen("tcp", fmt.Sprintf("localhost:%d", port))
	// if err != nil {
	// 	log.Fatalf("failed to listen on port %d: %v\n", port, err)
	// }

	// var opts []grpc.ServerOption
	// // Instantiate the gRPC server
	// grpcServer := grpc.NewServer(opts...)
	// // Register the service implementation w/ the gRPC server
	// pb.RegisterSwapsServer(grpcServer, newServer())
	// // Serve incoming requests
	// grpcServer.Serve(lis)

}
