<g:if test="${flash.error}">
    <div class="alert alert-error">
        <strong><g:message code="com.billmate.message.error" default="Oh snap!"/></strong>
        ${flash.error}
    </div>
</g:if>
<g:if test="${flash.message}">
    <div class="alert alert-info">
        <strong><g:message code="com.billmate.message.info" default="Heads up!"/></strong>
        ${flash.message}
    </div>
</g:if>