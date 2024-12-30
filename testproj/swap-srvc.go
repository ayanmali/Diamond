/*
 * Microservice allowing users to swap to and from stables and spot tokens seamlessly
 */

package main

import (
	"encoding/json"
	"net/http"
)

func HomeRouteFunc(w http.ResponseWriter, r *http.Request) {
	//w.Write([]byte("Testing Diamond microservice"))
	test := map[string]string{"result": "Testing Diamond JSON microservice"}
	w.Header().Add("Content-Type", "application/json")
	json.NewEncoder(w).Encode(test)
}
