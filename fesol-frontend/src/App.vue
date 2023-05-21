<script setup>
const name = 'Vue dinamico'
const arrayFrutas = [
  {name: "Manzana",
   price: "$1.00",
   description: "Una manzana"
  },

  {name: "Pera",
   price: "$2.00",
   description: "Una pera"
  },

  {name: "Naranja",
   price: "$3.00",
   description: "Una nanaranja"
  },

]

</script>

<template>
  <h1>Hola {{ name.toUpperCase() }}</h1>
  
  <ul>
    <li v-for="fruta in arrayFrutas" :key="fruta.name"> {{ fruta.name}} - {{ fruta.price }} - {{ fruta.description }} </li>
  </ul>
  
</template>

<style>
h1 {
  color: brown;
}
</style>