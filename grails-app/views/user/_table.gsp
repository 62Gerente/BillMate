<table class="table table-home-box table-home-box-fixed">
    <tbody>
        <g:if test="${users.isEmpty()}">
            <g:render template="/user/table/empty"/>
        </g:if>
        <g:else>
            <g:render template="/user/table/index" model="users: users"/>
        </g:else>
    </tbody>
</table>
