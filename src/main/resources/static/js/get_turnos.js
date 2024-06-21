async function deleteBy(id){
      const data = await apiCall(`/turnos/${id}`,'DELETE')
       cargarTurnos();
}
async function crearTurno(){
        const pacienteModal = document.querySelector("#pacienteModal")
        const odontologoModal = document.querySelector("#odontologoModal")
        const fechaModal = document.querySelector("#fechaModal")
        const body =
            {
                pacienteId:pacienteModal.value,
                odontologoId:odontologoModal.value,
                fecha:fechaModal.value
            }
        const data= await apiCall(`/turnos`,'POST',body)
        cargarTurnos();
}

async function actualizarTurno(){
           const id= +document.querySelector('#turnoId').value
           const body={
               id,
               pacienteId:+document.querySelector('#pacienteModal').value,
               odontologoId:+document.querySelector('#odontologoModal').value,
               fecha:document.querySelector('#fechaModal').value
           }
           console.log("bodysito: ", body)
           const data= await apiCall(`/turnos/${id}`,'PUT',body)
           cargarTurnos();

}

 function findBy(id,pacienteId,odontologoId,fecha){
        const idGroup = document.querySelector("#idGroup")
        const odontologoModal = document.querySelector("#odontologoModal")
        const pacienteModal = document.querySelector("#pacienteModal")
        const fechaModal = document.querySelector("#fechaModal")
        const modalCrearFooter = document.querySelector("#modalCrearFooter")
        idGroup.innerHTML = `<label for="turnoId" class="col-form-label">ID Turno:</label> <input type="text" class="form-control" id="turnoId" disabled value="${id}">`
        pacienteModal.value = `${pacienteId}`
        odontologoModal.value = `${odontologoId}`
        fechaModal.value = `${fecha}`
        modalCrearFooter.innerHTML = `<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button> <button type="button" onclick="actualizarTurno()" class="btn btn-primary" data-dismiss="modal">Guardar</button>`

}

async function modalCrearTurno(){
    const idGroup = document.querySelector("#idGroup")
    const pacienteModal = document.querySelector("#pacienteModal")
    const odontologoModal = document.querySelector("#odontologoModal")
    const fechaModal = document.querySelector("#fechaModal")
    const modalCrearFooter = document.querySelector("#modalCrearFooter")
    idGroup.innerHTML = ""
    pacienteModal.value = ""
    odontologoModal.value = ""
    fechaModal.value = ""
    modalCrearFooter.innerHTML = `<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button> <button type="button" onclick="crearTurno()" class="btn btn-primary" data-dismiss="modal">Guardar</button>`
}

async function cargarListaDesplegable(){
    const pacientesPromesa = apiCall('/pacientes' , 'GET')
    const odontologosPromesa = apiCall('/odontologos' , 'GET')
    const promesa = Promise.all ([pacientesPromesa,odontologosPromesa])
    const [pacientes, odontologos] = await promesa
    const listaPacientes = document.querySelector("#pacienteModal")
    listaPacientes.innerHTML = "<option selected>Seleccione el paciente </option>"
    pacientes.forEach((paciente) =>{
        listaPacientes.innerHTML +=`
        <option value="${paciente.id}">${paciente.nombre} ${paciente.apellido} CC.${paciente.cedula} </option>
        `
    })

    const listaOdontologos = document.querySelector("#odontologoModal")
        listaOdontologos.innerHTML = "<option selected>Seleccione el odontólogo </option>"
        odontologos.forEach((odontologo) =>{
            listaOdontologos.innerHTML +=`
            <option value="${odontologo.id}">${odontologo.nombre} ${odontologo.apellido} #Mat.${odontologo.numeroMatricula} </option>
            `
        })
}

async function cargarTurnos(){
    const data = await apiCall(`/turnos`,'GET')
    const tBody = document.querySelector("#turnosTableBody")
    tBody.innerHTML = "";
    data.forEach((turno)=>{
 //    var turnoRow = tBody.insertRow();
        tBody.innerHTML +=
        `<td>${turno.id} </td>
         <td id="pacienteId-${turno.paciente.id}" data-toggle="modal" data-target="#detallesModal">${turno.paciente.nombre} ${turno.paciente.apellido}  </td>
         <td id="odontologoId-${turno.odontologo.id}" data-toggle="modal" data-target="#detallesModal">${turno.odontologo.nombre} ${turno.odontologo.apellido}  </td>
           <td>${turno.fecha}  </td>
        <td> <button id= "btn-${turno.id}"  type="button" onclick="findBy(${turno.id}, ${turno.paciente.id},${turno.odontologo.id}, '${turno.fecha}')" class="btn btn-info"  data-toggle="modal" data-target="#crearModal">
             <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 24 24"><path fill="currentColor" d="M5 21q-.825 0-1.412-.587T3 19V5q0-.825.588-1.412T5 3h8.925l-2 2H5v14h14v-6.95l2-2V19q0 .825-.587 1.413T19 21zm4-6v-4.25l9.175-9.175q.3-.3.675-.45t.75-.15q.4 0 .763.15t.662.45L22.425 3q.275.3.425.663T23 4.4t-.137.738t-.438.662L13.25 15zM21.025 4.4l-1.4-1.4zM11 13h1.4l5.8-5.8l-.7-.7l-.725-.7L11 11.575zm6.5-6.5l-.725-.7zl.7.7z"/></svg>
             </button> </td>
        <td> <button id= "btn-${turno.id}"  type="button" onclick="deleteBy(${turno.id})" class="btn btn-danger btn_delete">
                            <span aria-hidden="true">&times;</span>
                          </button> </td>
        `
        document.querySelector(`#pacienteId-${turno.paciente.id}`).addEventListener("click",function(){
        document.querySelector("#modalTitle").innerHTML = "Información del paciente"
        document.querySelector("#modalDetailsBody").innerHTML = `
             <table id="pacienteTable" class="table  table-hover">
                      <tbody id="pacienteTableBody">
                        <tr>
                          <td>ID</td>
                          <td>${turno.paciente.id}</td>
                        </tr>
                        <tr>
                          <td>Nombre</td>
                          <td>${turno.paciente.nombre} ${turno.paciente.apellido}</td>
                        </tr>
                        <tr>
                          <td>Email</td>
                          <td>${turno.paciente.email}</td>
                        </tr>
                      </tbody>
                  </table>

        `
           })
         document.querySelector(`#odontologoId-${turno.odontologo.id}`).addEventListener("click",function(){
                document.querySelector("#modalTitle").innerHTML = "Información del odontologo"
                document.querySelector("#modalDetailsBody").innerHTML = `
                     <table id="pacienteTable" class="table  table-hover">
                              <tbody id="pacienteTableBody">
                                <tr>
                                  <td>ID</td>
                                  <td>${turno.odontologo.id}</td>
                                </tr>
                                <tr>
                                  <td>Nombre</td>
                                  <td>${turno.odontologo.nombre} ${turno.odontologo.apellido}</td>
                                </tr>
                                <tr>
                                  <td>Número Matrícula</td>
                                  <td>${turno.odontologo.numeroMatricula}</td>
                                </tr>
                              </tbody>
                          </table>
                `
        })

})
}

window.addEventListener('load', async function () {
     cargarTurnos();
     cargarListaDesplegable();
})