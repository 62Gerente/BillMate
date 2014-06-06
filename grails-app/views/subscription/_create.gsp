<g:form url="[action: 'save', controller: 'subscription']" id="subscribe-form">
    <div class="row">
        <div class="col-xs-12">
            <div class="alert hidden"></div>
        </div>
    </div>
    <div class="row form-row">
        <div class="col-sm-8 col-xs-12">
            <input name="email" type="text" class="form-control" placeholder="<g:message code="com.billmate.subscription.email" default="E-mail"/>">
        </div>
        <div class="col-sm-4 col-xs-12">
            <button type="submit" class="btn btn-primary btn-cons full-width"><g:message code="com.billmate.subscription.subscribe" default="Subscribe"/></button>
        </div>
    </div>
</g:form>
