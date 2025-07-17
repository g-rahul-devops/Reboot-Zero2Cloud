provider "google" {
  project     = "red-welder-466202-u6"
  region      = "asia-south1"
  credentials = file("service-account.json")
}

resource "google_storage_bucket" "my_bucket" {
  name     = "my-dynamic-bucket-12345"
  location = "ASIA"
}
