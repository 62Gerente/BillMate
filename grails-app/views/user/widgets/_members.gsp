<div class="row tiles-container tiles white hidden-xlg m-b-20">
    <div class="col-md-12 b-grey b-r no-padding">
        <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
            <h4 class="text-black bold inline uppercase"><g:message code="com.billmate.user.widget.members.title" default="Members" /></h4>
            <div class="pull-right p-r-15" id="home-button-primary-confirm">
                <h4>
                    <i class="fa fa-cog text-grey p-r-5"></i>
                    <i class="fa fa-plus"></i>
                </h4>
            </div>
        </div>
        <div class="p-l-20 p-r-20">
          <g:render template="/user/table" model="[users: members]"/>
        </div>
    </div>
</div>
