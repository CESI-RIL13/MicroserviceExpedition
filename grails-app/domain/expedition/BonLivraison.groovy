package expedition

import grails.rest.Resource

@Resource(formats=['html','json', 'xml'])
class BonLivraison {

    Date date
    String emetteur
    String adresse_emetteur
    String destinataire
    String adresse_destination
    Boolean verifier_conforme
    String annotation

    static hasMany = [produits: Produit]

    static constraints = {
        annotation nullable: true
    }
}
