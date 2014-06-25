<div class="col-md-6">
    <div class="row tiles-container tiles white hidden-xlg m-b-20">
        <div class="col-md-12 b-grey b-r no-padding widget-table-adjust-right-margin">
            <div class="p-l-20 p-r-20 p-b-10 p-t-10 b-b b-grey">
                <h4 class="text-black bold inline uppercase">
                    Fatura
                </h4>
            </div>
                <g:if test="${expense.getInvoice()}">
                    <div class="p-l-20 p-r-20 p-t-5 p-b-5 b-b b-grey">
                        <div class="document-container">
                            <div class="iframe-border"></div>
                            <div class="iframe-border iframe-border-side pull-left"></div>
                            <iframe class="document pull-left" height="300px" width="100%" src="${createLink(controller: "fileUploader", action: "show", id: expense.getInvoice().getId())}" frameborder=0 ALLOWTRANSPARENCY="true"></iframe>
                            <div class="iframe-border iframe-border-side pull-left"></div>
                            <div class="clearfix"></div>
                            <div class="iframe-border"></div>
                        </div>
                    </div>
                </g:if>
                <g:elseif test="${expense.getResponsible().getId() == registeredUser.getId()}">
                    <div class="b-b b-grey">
                        <div class="text-center p-l-20 p-r-20 p-t-100 p-b-5 upload-box btn-upload-hover pointer" id="upload-invoice">
                            <i class="fa fa-file-pdf-o fa-5x"></i>
                            <h5 class="text-muted">Carregar recibo</h5>
                        </div>
                        <form enctype="multipart/form-data" action="${createLink(controller: "fileUploader", action: "process", id: expense.getId())}" id="upload-invoice-form" style="display: none">
                            <input type="hidden" name="upload" value="document">
                            <input type="hidden" name="errorAction" value="errorUpload">
                            <input type="hidden" name="errorController" value="expense">
                            <input type="hidden" name="successAction" value="successUploadInvoice">
                            <input type="hidden" name="successController" value="expense">
                            <input type="file" name="file" id="upload-invoice-input">
                        </form>
                    </div>
                    <div class="p-l-20 p-r-20 p-t-5 p-b-5 b-b b-grey" id="invoice-container" style="display: none">
                        <div class="document-container">
                            <div class="iframe-border"></div>
                            <div class="iframe-border iframe-border-side pull-left"></div>
                            <iframe id="invoice-iframe" class="document pull-left" height="300px" width="100%" src="" frameborder=0 ALLOWTRANSPARENCY="true"></iframe>
                            <div class="iframe-border iframe-border-side pull-left"></div>
                            <div class="clearfix"></div>
                            <div class="iframe-border"></div>
                        </div>
                    </div>
                </g:elseif>
                <g:else>
                    <div class="b-b b-grey">
                        <div class="text-center p-l-20 p-r-20 p-t-30 p-b-30">
                            <i class="fa fa-frown-o fa-5x"></i>
                            <h5 class="text-muted">Recibo indisponível</h5>
                        </div>
                    </div>
                </g:else>
                <div class="clearfix"></div>
        </div>
    </div>
</div>
