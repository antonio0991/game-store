<template>
  <body>
    <h1>Game Store</h1>
    <div class = "lista-wrapper">
      <div>
        <Listagem/>
      </div>
    </div>
  </body>
</template>

<script>
import Listagem from './components/Listagem.vue';

export default {
  name: 'App',
  components: {
    Listagem
  }
}
</script>

<style>
  body {
    background-color: rgb(49, 49, 49) !important; 
    color: rgb(144, 75, 255) !important;
    height: 100%;
    width: 99%;
  }

  .lista-wrapper {
    width: 100%;
    padding-left: 10%;
    padding-right: 10%;
  }
</style>
