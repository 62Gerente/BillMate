<g:each var="expense" in="${expenses}">
    <g:render template="/expense/report/documents/show" model="[expense: expense]"/>
</g:each>
