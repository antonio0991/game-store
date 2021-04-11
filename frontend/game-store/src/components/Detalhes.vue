<template>
    <div>
        <h5>Preço: R${{game.preco}}</h5>
        <h5>Cópias vendidas: {{game.numVendas}}</h5>
    </div>
</template>

<script>
export default {
    name: 'Detalhes',

    props: {
            game: {
                type: Object,
                default: function () {
                    return {
                    id: 0,
                    titulo: 'titulo',
                    imgURL: 'imgURL',
                    preco: 0.00,
                    numVendas: 0
                    }
                }
            }
        },
}

</script>

<style lang="stylus">

</style>