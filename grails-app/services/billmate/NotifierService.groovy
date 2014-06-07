package billmate

import grails.transaction.Transactional

@Transactional
class NotifierService {

    def mailService

    def send(emailAddress, mailSubject, mailBody) {
        mailService.sendMail {
            async true
            to emailAddress
            subject mailSubject
            body mailBody
        }
    }
}
