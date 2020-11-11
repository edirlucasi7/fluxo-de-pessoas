axios.get('http://localhost:8080/api/v1/pessoa/listar')
  .then(function(response) {
    for(res of response.data) {
      var ulElemnt = document.querySelector('.ulList')
      var list = document.createElement('li')

      ulElemnt.appendChild(list)

      var nomePessoa = res.nome
      var cpfPessoa = res.cpf
      var emailPessoa = res.email

      var nome = document.createTextNode(`Nome: ${nomePessoa} `)
      var cpf = document.createTextNode(`Cpf: ${cpfPessoa} `)
      var email = document.createTextNode(`Email: ${emailPessoa}`)

      list.appendChild(nome)
      list.appendChild(cpf)
      list.appendChild(email)
    }
  })
  .catch(function(error) {
    console.log(error)
    console.log("deu ruim")
  })

