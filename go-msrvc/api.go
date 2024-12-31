/*
 * Microservice for currency swaps to and from stables and spot tokens
 */

package main

import "net/http"

func testing(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("Welcome"))
	w.Header().Add("Content-Type", "application/json")
}
