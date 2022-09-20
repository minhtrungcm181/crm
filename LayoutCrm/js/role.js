$(document).ready(function() {
 $.ajax({
    url: "http://localhost:8080/crm_war/api/role",
    method: "GET"

 }).done(function(result){
    $("#example tbody").empty()
    $.each(result, function(index,value){
        console.log(value)
       var row = `<tr>
            <td>${value.id}</td>
            <td>${value.name}</td>
            <td>${value.description}</td>
           <td>
           <a href="#" class="btn btn-sm btn-primary">Sửa</a>
             <a href="#" class="btn btn-sm btn-danger btn-delete" role-id = "${value.id}">Xóa</a>
                                            </td>
                                        </tr>`
        $("#example tbody").append(row)
    })
 })
 
 $('body').on('click','.btn-delete',function(){
   var roleId = $(this).attr('role-id')
   console.log(`Role id ${roleId}`)
   var This = $(this)

   $.ajax({
      url: `http://localhost:8080/crm_war/api/role?id=${roleId}`,
      method: "DELETE", 
   }).done(function(result){
      console.log("kiemtra")
      console.log(result)
      if (result.isSuccess == true) {
         console.log("xoa thanh cong")
         This.closest('tr').remove()
      }
      else {
         console.log("xoa that bai")
      }

   })
 })
 $('#btn-save-role').click(function(e){
   e.preventDefault()
   
   var dataRole = $('#role').val()
   var dataDescription = $('#description').val()
   
        
          if (dataDescription == "" || dataRole == "") {
            alert("Vui lòng nhập đầy đủ tên và mô tả");
            return false
          }
        
      
  
   $.ajax({
      url: `http://localhost:8080/crm_war/api/role`,
      method: "POST", 
      data: {
         role: dataRole, 
         description: dataDescription
      },
   }).done(function(result){
      console.log("kiemtra")
      console.log(result)
      if (result.isSuccess == true) {
         console.log("them thanh cong")
         dataRole = $('#role').val("")
         dataDescription = $('#description').val("")
        $.toast({
         heading: 'Success',
         text: 'Bạn đã thêm thành công một vai trò',
         showHideTransition:'slide',
         position:'mid-center',
         icon: 'success'
        })
      }
      else {
         console.log("them that bai")
      }

   })
 })
})