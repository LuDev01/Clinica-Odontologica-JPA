 const apiCall= async (url,method,body)=>{
 let settings;
     if(method == 'GET' || method == 'DELETE'){
        settings = {
                      method: method,
                        headers: {
                              "Content-Type": "application/json",
                              // 'Content-Type': 'application/x-www-form-urlencoded',
                            },
                      }
     }
     else{
          settings = {
              method: method,
                headers: {
                      "Content-Type": "application/json",
                      // 'Content-Type': 'application/x-www-form-urlencoded',
                    },
                body: JSON.stringify(body)
              }
     }

      return await fetch(url,settings).then(response =>
      {
      try {
         console.log({response})
        // const res= JSON.parse(response.body)
        const res= response.json()
         return res;
      }catch (e){
         console.log("Error!", e)
         return response;
      }

      })

 }

