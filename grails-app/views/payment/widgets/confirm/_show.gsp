<tr>
    <td class="home-img-list-confs">
        <div class="friend-profile-pic">
            <div class="user-profile-pic-normal">
                <img width="35" height="35" src="${payment.getUser().getPhotoOrDefault()}" alt="">
            </div>
        </div>
    </td>
    <td class="unseen">${payment.getUser()}</td>
    <td class="unseen">${payment.getExpense().getCircle()}</td>
    <td><i style="width: 15px" class="${payment.getExpense().getExpenseType().getCssClass()}"></i> ${payment.getExpense()}</td>
    <td class="text-right"><g:formatNumber number="${payment.getValue()}" type="currency" currencyCode="EUR" /></td>
    <td id="home-check-list">
        <div class="row-fluid home-check-confirm">
            <div class="checkbox check-success">
                <input type="checkbox" id="id1" class="todo-list">
                <label class="pull-right" for="id1" style="margin-right: 25px"></label>
            </div>
        </div>
    </td>
</tr>