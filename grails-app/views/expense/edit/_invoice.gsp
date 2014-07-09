<div class="col-md-6">
    <div class="row tiles-container tiles white hidden-xlg m-b-20">
        <div class="col-md-12 b-grey b-r no-padding widget-table-adjust-right-margin">
            <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
                <h4 class="text-black bold inline uppercase">
                    <g:message code="com.billmate.expense.invoice" default="Invoice"/>
                </h4>
                <h4 class="inline pull-right">
                    <g:if test="${expense.getInvoice()}">
                        <g:if test="${expense.getResponsible().getId() == registeredUser.getId()}">
                            <a href="#" class="upload-invoice-icon"><i class="fa fa-pencil text-grey utility-icon hover-icon-blg"></i></a>
                        </g:if>

                        <a class="download-invoice" target="_blank" href="${createLink(controller: "fileUploader", action: "show", id: expense.getInvoice().getId())}"><i class="fa fa-download text-grey utility-icon hover-icon-blg"></i></a>
                    </g:if>
                    <g:else>
                        <a style="display: none" href="#" class="upload-invoice-icon"><i class="fa fa-pencil text-grey utility-icon hover-icon-blg"></i></a>
                        <a class="download-invoice" style="display: none" target="_blank" href=""><i class="fa fa-download text-grey utility-icon hover-icon-blg"></i></a>
                    </g:else>
                </h4>
            </div>
                <g:if test="${expense.getInvoice()}">
                    <div class="p-l-20 p-r-20 p-t-5 p-b-5 b-b b-grey">
                        <div class="document-container">
                            <div class="iframe-border"></div>
                            <div class="iframe-border iframe-border-side pull-left"></div>
                            <iframe class="document pull-left invoice-iframe" height="300px" width="100%" src="${createLink(controller: "fileUploader", action: "show", id: expense.getInvoice().getId())}" frameborder=0 ALLOWTRANSPARENCY="true"></iframe>
                            <div class="iframe-border iframe-border-side pull-left"></div>
                            <div class="clearfix"></div>
                            <div class="iframe-border"></div>
                        </div>
                    </div>
                </g:if>
                <g:elseif test="${expense.getResponsible().getId() == registeredUser.getId()}">
                    <div class="b-b b-grey">
                        <div class="text-center p-l-20 p-r-20 p-t-100 p-b-5 upload-box btn-upload-hover pointer upload-invoice">
                            <i class="fa fa-file-pdf-o fa-5x"></i>
                            <h5 class="text-muted"><g:message code="com.billmate.expense.invoice.upload" default="Upload invoice"/></h5>
                        </div>
                    </div>
                    <div class="p-l-20 p-r-20 p-t-5 p-b-5 b-b b-grey invoice-container" style="display: none">
                        <div class="document-container">
                            <div class="iframe-border"></div>
                            <div class="iframe-border iframe-border-side pull-left"></div>
                            <iframe class="document pull-left invoice-iframe" height="300px" width="100%" src="" frameborder=0 ALLOWTRANSPARENCY="true"></iframe>
                            <div class="iframe-border iframe-border-side pull-left"></div>
                            <div class="clearfix"></div>
                            <div class="iframe-border"></div>
                        </div>
                    </div>
                </g:elseif>
                <g:else>
                    <div class="b-b b-grey">
                        <div class="text-center p-l-20 p-r-20  p-t-100 p-b-5 upload-box">
                            <i class="fa fa-frown-o fa-5x"></i>
                            <h5 class="text-muted"><g:message code="com.billmate.expense.invoice.unavailable" default="Invoice unavailable"/></h5>
                        </div>
                    </div>
                </g:else>
                <form enctype="multipart/form-data" action="${createLink(controller: "fileUploader", action: "process", id: expense.getId())}" id="upload-invoice-form" style="display: none">
                    <input type="hidden" name="upload" value="document">
                    <input type="hidden" name="errorAction" value="errorUpload">
                    <input type="hidden" name="errorController" value="expense">
                    <input type="hidden" name="successAction" value="successUploadInvoice">
                    <input type="hidden" name="successController" value="expense">
                    <input type="file" name="file" id="upload-invoice-input">
                </form>
                <div class="clearfix"></div>
        </div>
    </div>
</div>
