axios.get('http://localhost:8080/api/v1/pessoa/listar')
  .then(function(response) {
    console.log(response)
  })
  .catch(function(error) {
    console.log(error)
    console.log("deu ruim")
  })