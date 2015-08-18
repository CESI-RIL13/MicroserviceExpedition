package expedition

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BonLivraisonController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BonLivraison.list(params), model:[bonLivraisonCount: BonLivraison.count()]
    }

    def show(BonLivraison bonLivraison) {
        respond bonLivraison
    }

    def create() {
        respond new BonLivraison(params)
    }

    @Transactional
    def save(BonLivraison bonLivraison) {
        if (bonLivraison == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (bonLivraison.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond bonLivraison.errors, view:'create'
            return
        }

        bonLivraison.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bonLivraison.label', default: 'BonLivraison'), bonLivraison.id])
                redirect bonLivraison
            }
            '*' { respond bonLivraison, [status: CREATED] }
        }
    }

    def edit(BonLivraison bonLivraison) {
        respond bonLivraison
    }

    @Transactional
    def update(BonLivraison bonLivraison) {
        if (bonLivraison == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (bonLivraison.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond bonLivraison.errors, view:'edit'
            return
        }

        bonLivraison.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'bonLivraison.label', default: 'BonLivraison'), bonLivraison.id])
                redirect bonLivraison
            }
            '*'{ respond bonLivraison, [status: OK] }
        }
    }

    @Transactional
    def delete(BonLivraison bonLivraison) {

        if (bonLivraison == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        bonLivraison.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'bonLivraison.label', default: 'BonLivraison'), bonLivraison.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bonLivraison.label', default: 'BonLivraison'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
