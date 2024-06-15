function findBy (id){
    window.location.href = `http://localhost:8080/post_pacientes.html?id=${id}`
}
async function deleteById (id){
     const data=await apiCall(`/pacientes/${id}`,'DELETE')
     loadPage();
}
function loadPage() {
          const url = '/pacientes';
          const settings = {
            method: 'GET'
          }

          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
                var table = document.getElementById("pacienteTableBody");
                table.innerHTML = "";
             for(paciente of data){
                var pacienteRow =table.insertRow();
                let tr_id = 'tr_' + paciente.id;
                pacienteRow.id = tr_id;

                let deleteButton = '<button' +
                                          ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                          ' type="button" onclick="deleteById('+paciente.id+')" class="btn btn-danger btn_delete">' +
                                          '&times' +
                                          '</button>';

                let updateButton = '<button' +
                                          ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                          ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                                          '<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 24 24"><path fill="currentColor" d="M5 21q-.825 0-1.412-.587T3 19V5q0-.825.588-1.412T5 3h8.925l-2 2H5v14h14v-6.95l2-2V19q0 .825-.587 1.413T19 21zm4-6v-4.25l9.175-9.175q.3-.3.675-.45t.75-.15q.4 0 .763.15t.662.45L22.425 3q.275.3.425.663T23 4.4t-.137.738t-.438.662L13.25 15zM21.025 4.4l-1.4-1.4zM11 13h1.4l5.8-5.8l-.7-.7l-.725-.7L11 11.575zm6.5-6.5l-.725-.7zl.7.7z"/></svg>' +
                                          '</button>';

                pacienteRow.innerHTML = '<td>' + paciente.id + '</td>' +
                        '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                        '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                        '<td class=\"td_cedula\">' + paciente.cedula.toUpperCase() + '</td>' +
                        '<td class=\"td_fechaIngreso\">' + paciente.fechaIngreso + '</td>' +
                         '<td class=\"td_calle\">' + paciente.domicilio.calle.toUpperCase() + '</td>' +
                        '<td class=\"td_email\">' + paciente.email.toUpperCase() + '</td>' +
                        '<td>' + updateButton + '</td>'+
                        '<td>' + deleteButton + '</td>';

            };

        })
}
window.addEventListener('load', function () {
    loadPage();

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_pacientes.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })