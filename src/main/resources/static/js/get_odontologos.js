  async function deleteBy(id){
           try
           {
            const data=await apiCall(`/odontologos/${id}`,'DELETE',null)
           }
           catch{
           console.log('Delete')
           }

           // location.reload();
           cargarListaOdontologos();
        }
  function  modalActualizarId(id,numeroMatricula,nombre,apellido){
        document.querySelector("#formulario").reset()
        console.log({id,numeroMatricula,nombre,apellido});
        //const odontologoIdField=document.querySelector('#odontologoId')
        const numeroMatriculaField=document.querySelector('#numeroMatricula')
        const nombreField=document.querySelector('#nombre')
        const apellidoField=document.querySelector('#apellido')
        const footer=document.querySelector(".modal-footer")
        const idGroup=document.querySelector("#idGroup")
        const title=document.querySelector('#exampleModalLabel')
        title.innerHTML='Actualizar Odontologo'
        idGroup.innerHTML='<label for="odontologoId" class="col-form-label">ID Odontologo:</label>  <input type="text" class="form-control" id="odontologoId" disabled value= "' + id + '"> '
        //odontologoIdField.value=id;
        numeroMatriculaField.value = numeroMatricula;
        nombreField.value = nombre;
        apellidoField.value = apellido;
        footer.innerHTML= '<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>  <button type="button" onclick="guardarCambios()" class="btn btn-primary" data-dismiss="modal">Guardar</button>'

  }

    function modalCrearOdontologo(){
           document.querySelector("#formulario").reset()
           const footer=document.querySelector(".modal-footer")
           const title=document.querySelector('#exampleModalLabel')
           const idGroup=document.querySelector("#idGroup")
           footer.innerHTML= '<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button> <button type="button" onclick="crearOdontologo()" class="btn btn-primary" data-dismiss="modal">Guardar</button>'
           title.innerHTML='Crear Odontologo'
           idGroup.innerHTML=""
      }

  async function crearOdontologo(){
        const body={
            numeroMatricula:document.querySelector('#numeroMatricula').value,
            nombre:document.querySelector('#nombre').value,
            apellido:document.querySelector('#apellido').value
        }

        const data= await apiCall(`/odontologos`,'POST',body)
        cargarListaOdontologos();
  }

  async function guardarCambios(){
        const body={
            id:document.querySelector('#odontologoId').value,
            numeroMatricula:document.querySelector('#numeroMatricula').value,
            nombre:document.querySelector('#nombre').value,
            apellido:document.querySelector('#apellido').value
        }
        console.log("cambiesitos: ",body)
        const data= await apiCall(`/odontologos`,'PUT',body)
        cargarListaOdontologos();
  }
 function cargarListaOdontologos(){

      //con fetch invocamos a la API de peliculas con el método GET
      //nos devolverá un JSON con una colección de peliculas
      const url = '/odontologos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de peliculas del JSON
      console.log("Los daticos: ",data);
      document.getElementById("odontologoTableBody").innerHTML="";
         for(odontologo of data){
            //por cada pelicula armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula
            var table = document.getElementById("odontologoTableBody");
            console.log(table);
           // table.innerHTML = ""
            var odontologoRow =table.insertRow();
            let tr_id = odontologo.id;
            odontologoRow.id = tr_id;


            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                      ' type="button" onclick="deleteBy('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                      ' type="button" onclick="modalActualizarId('+ `${odontologo.id},${odontologo.numeroMatricula},'${odontologo.nombre}','${odontologo.apellido}'` +')" class="btn btn-info btn_id" data-toggle="modal" data-target="#exampleModal">' +
                                      '<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 24 24"><path fill="currentColor" d="M5 21q-.825 0-1.412-.587T3 19V5q0-.825.588-1.412T5 3h8.925l-2 2H5v14h14v-6.95l2-2V19q0 .825-.587 1.413T19 21zm4-6v-4.25l9.175-9.175q.3-.3.675-.45t.75-.15q.4 0 .763.15t.662.45L22.425 3q.275.3.425.663T23 4.4t-.137.738t-.438.662L13.25 15zM21.025 4.4l-1.4-1.4zM11 13h1.4l5.8-5.8l-.7-.7l-.725-.7L11 11.575zm6.5-6.5l-.725-.7zl.7.7z"/></svg>' +
                                      '</button>';

            odontologoRow.innerHTML =
                    '<td class=\"td_id\">' + odontologo.id + '</td>' +
                    '<td class=\"td_matricula\">' + odontologo.numeroMatricula + '</td>' +
                    '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
                    '<td>' + updateButton + '</td>' +
                    '<td>' + deleteButton + '</td>';

        };

    })
    }



window.addEventListener('load', function () {
   cargarListaOdontologos();

   /* (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_odontologos.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })*/


    })