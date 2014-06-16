<div class="grid simple vertical ${circle.getCssColor()}">
    <div class="grid-body no-border">
        <div class="row-fluid ">
            <div class="p-t-20 p-r-20 p-b-30 p-l-20">
                <i class="${circle.getCssClass()} fa-3x"> </i>
                <h4 class="semi-bold text-dark-grey inline profile-circle-name p-l-10">
                    ${circle.getName()}
                </h4>
                <p>${circle.getDescription()}</p>
                <g:render template="/user/members" model="[members: circle.getUsers()]"/>
            </div>
        </div>
    </div>
</div>