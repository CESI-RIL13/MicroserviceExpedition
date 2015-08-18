package expedition

import grails.rest.Resource

@Resource(formats=['html','json', 'xml'])
class Produit {

    String ref_produit
    String ref_bon_commande
    Integer quantite
    Boolean verifier_conforme
    String annotation

    static belongsTo = [bonLivraison:BonLivraison]

    static constraints = {
        annotation nullable: true
    }
}
