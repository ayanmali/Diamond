package main

import "net/http"

func testing(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("Welcome"))
}
