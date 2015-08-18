class UrlMappings {

    static mappings = {

        "/bon-livraisons"(resources:"bonLivraison") {
            "/produits"(resources:"produit")
        }
        "/produits"(resources:"produit") {
            "/bon-livraison"(resource:"bonLivraison")
        }
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
