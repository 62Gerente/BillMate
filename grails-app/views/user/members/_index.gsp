<ul class="my-friends">
    <g:each var="member" in="${members}">
        <g:render template="/user/members/show" model="[member: member]"/>
    </g:each>
</ul>
