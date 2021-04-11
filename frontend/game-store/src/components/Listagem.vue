<template>
    <div>
        <div class="card w-75" style="background-color: rgb(49, 49, 49); border: 3px solid rgb(20, 20, 20); height: 85px; width: 100% !important">
            <div class="card-body" style="display: flex; justify-content: space-between">
                <h2 class="card-title">Games</h2>
                <a href="#" class="btn btn-success" data-toggle="modal" data-target="#formulario">Novo Game</a>
            </div>
        </div>
       

        <div style="margin-top: 10px;">
           <ul id = "lista-games">
                <li v-for="(g, index) in games" :key = "index">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="padding: 0px; border: 0px"
                    v-on:click="selectGame(index)">
                        <img :src="g.imgURL" class = "capa">
                    </button>
                </li>
            </ul> 
        </div>

        <div class="modal" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content" style="background-color: rgb(49, 49, 49); border-width: 0px">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">{{selectedGame.titulo}}</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <Detalhes :game=selectedGame />
                        <div style="display: flex; margin-top: 8px">
                            <h6>Vender múltiplos?</h6>
                            <input type="checkbox" style="margin-top: 5px; margin-left: 8px" v-on:click="mostrarVendasMultiplas()">
                        </div>
                        <div v-if="mostrarQuantidade" style="margin-top: 8px">
                                <label for="quantidade">Quantidade</label>
                                <input type="number" id="quantidade" style="margin-left: 8px; width: 60px; height: 25px" v-model="quantidade">
                        </div>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" v-on:click="venderJogo()">Realizar venda</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal"  v-on:click="deletarJogo()">Deletar</button>
                    </div>

                </div>
            </div>
        </div>


        <div class="modal" id="formulario">
            <div class="modal-dialog">
                <div class="modal-content" style="background-color: rgb(49, 49, 49); border-width: 0px">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Novo Game</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        <Formulario/>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import axios from 'axios';
import Detalhes from './Detalhes'
import Formulario from './Formulario'

export default {
    name: 'Listagem',

    components:{
        Detalhes,
        Formulario
    },

    data() {
        return{
            games: [],
            selectedGame: Object,
            mostrarQuantidade: Boolean,
            quantidade: Number,
        }
    },

    methods: {
        selectGame: function(index){
            this.selectedGame = this.games[index];
        },

        venderJogo: function(){
            if(this.mostrarQuantidade){
                axios.get('http://localhost:8080/v1/games/' + this.selectedGame.id + '/buy/' + this.quantidade);
                this.selectedGame.numVendas = parseInt(this.selectedGame.numVendas, 10) + parseInt(this.quantidade, 10);
            }
            else{
                axios.get('http://localhost:8080/v1/games/' + this.selectedGame.id + '/buy');
                this.selectedGame.numVendas++;
            }
        },

        mostrarVendasMultiplas: function(){
            this.mostrarQuantidade = !this.mostrarQuantidade;
        },

        deletarJogo: function(){
            axios.delete('http://localhost:8080/v1/games/' + this.selectedGame.id);
            location.reload();
        }

    },

    created: function(){
        axios.get('http://localhost:8080/v1/games').then(response =>{
            this.games = response.data.dados;
            this.selectedGame = this.games[0];
        })
        .catch(error =>{
            console.log(error);
        })
        this.mostrarQuantidade = false;
    },

}

</script>

<style scoped>
  
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    display: flex;
    flex-wrap: wrap;
  }
ul li {
    margin-left: 3%;
    margin-bottom: 1.5em;
    display: flex;
    flex-direction: row;
  }
.capa {
    float: left;
    width:  10.3em;
    height: 16em;
    object-fit: cover;
}
</style>