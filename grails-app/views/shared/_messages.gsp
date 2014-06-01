<g:if test="${flash.error}">
    <div class="alert alert-error">
        %{--<strong><g:message code="com.billmate.message.error" default="Oh snap!"/></strong>--}%
        <g:message code="${flash.error}" args="${flash.e_args}" default="${flash.e_default}"/>
    </div>
</g:if>
<g:elseif test="${flash.message}">
    <div class="alert alert-info">
        %{--<strong><g:message code="com.billmate.message.info" default="Heads up!"/></strong>--}%
        <g:message code="${flash.message}" args="${flash.m_args}" default="${flash.m_default}"/>
    </div>
</g:elseif>
