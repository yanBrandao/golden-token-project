resource "kubernetes_namespace" "example" {
  metadata {
    name = "k8s-ns-by-tf"
  }
}

resource "kubernetes_deployment" "example" {
  metadata {
    name = "terraform-example"
    labels = {
      test = "MyExampleApp"
    }
    namespace = "k8s-ns-by-tf"
  }