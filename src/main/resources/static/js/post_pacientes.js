window.addEventListener('load', async function () {
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const pacienteId = urlParams.get('id');
console.log(`Paciente id: ${pacienteId}`);

if(pacienteId)
{
    const data=await apiCall(`/pacientes/${pacienteId}`,'GET')
    console.log("daticos: ", data);
     document.querySelector('#nombre').value = data.nombre;
     document.querySelector('#apellido').value = data.apellido;
     document.querySelector('#cedula').value = data.cedula;
     document.querySelector('#fechaIngreso').value = data.fechaIngreso;
     document.querySelector('#calle').value = data.domicilio.calle;
     document.querySelector('#numero').value = data.domicilio.numero;
     document.querySelector('#localidad').value = data.domicilio.localidad;
     document.querySelector('#provincia').value = data.domicilio.provincia;
     document.querySelector('#email').value = data.email
}

    const formulario = document.querySelector('#add_new_paciente');
    formulario.addEventListener('submit', async function (event) {
        event.preventDefault();
            let formData = {
                nombre: document.querySelector('#nombre').value,
                apellido: document.querySelector('#apellido').value,
                cedula: document.querySelector('#cedula').value,
                fechaIngreso: document.querySelector('#fechaIngreso').value,
                domicilio:{
                        calle:document.querySelector('#calle').value,
                        numero: document.querySelector('#numero').value,
                        localidad: document.querySelector('#localidad').value,
                        provincia: document.querySelector('#provincia').value,
                             },
                email: document.querySelector('#email').value
            };

    //Editar
    if(pacienteId)
    {
       formData.id = pacienteId;
        const data=await apiCall(`/pacientes`,'PUT',formData)
        window.location.href = "http://localhost:8080/get_pacientes.html";
    }

    else
    {
        //Crear
            const url = '/pacientes';
            const settings = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            }
            fetch(url, settings)
                        .then(response => response.json())
                        .then(data => {
                             let successAlert = '<div class="alert alert-success alert-dismissible">' +
                                 '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                 '<strong></strong> Paciente agregado </div>'

                             //document.querySelector('#response').innerHTML = successAlert;
                             //document.querySelector('#response').style.display = "block";
                             //resetUploadForm();
                              window.location.href = "http://localhost:8080/get_pacientes.html";

                        })
                        .catch(error => {
//                                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
//                                                 '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
//                                                 '<strong> Error intente nuevamente</strong> </div>'

                                 console.log("Error!", e.message)
//                                 document.querySelector('#response').style.display = "block";
                                // resetUploadForm();
                                 window.location.href = "http://localhost:8080/get_pacientes.html";

                                 })
    }

            });

    function resetUploadForm(){
        document.querySelector('#titulo').value = "";
        document.querySelector('#categoria').value = "";
         document.querySelector('#premios').value = "";

    }

});