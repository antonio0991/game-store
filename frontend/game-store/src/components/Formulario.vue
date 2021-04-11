<template>
    <div>
        <form>
            <div class="form-group">
                <label for="titulo">Titulo</label>
                <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" v-model="game.titulo" required v-on:change="isValid()">
            </div>
            <div class="form-group">
                <label for="imgURL">URL da imagem da capa</label>
                <input type="text" class="form-control" id="imgURL" v-model="game.imgURL" v-on:change="isValid()" required>
            </div>
            <div class="form-group">
                <label for="preco">Preço</label>
                <money v-model="game.preco" v-bind="money" style="margin-left: 8px" required></money>
            </div>
            <div class="modal-footer" style="margin-bottom: -15px; margin-left: -15px; width: 106.55%">
                <button type="submit" class="btn btn-success" v-on:click="salvarJogo()" id="salvarBtn" disabled>Salvar Jogo</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
            </div> 
        </form>
    </div>
</template>

<script>
import {Money} from 'v-money'
import axios from 'axios';

export default {
     components: {Money},

    data () {
      return {
        game: {
            type: Object,
            default: function () {
                return {
                titulo: 'titulo',
                imgURL: 'imgURL',
                preco: 0.00,
                }
            }
        },
        
        money: {
          decimal: ',',
          thousands: '.',
          prefix: 'R$ ',
          suffix: '',
          precision: 2,
          masked: false
        }
      }
    },

    methods: {
        salvarJogo: function(){
            location.replace(location.host);
            axios.post('http://localhost:8080/v1/games/',{
                titulo: this.game.titulo,
                imgURL: this.game.imgURL,
                preco: this.game.preco,
                numVendas: 0
            });
        },

        isValid: function(){
            if(this.game.titulo && this.game.imgURL){
                document.getElementById("salvarBtn").disabled = false;
            }
            else{
                document.getElementById("salvarBtn").disabled = true;
            }
        },
    }

}
</script>