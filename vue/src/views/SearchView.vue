<template>
    <h2>Search for any plant by ID (as long as the ID is 1)</h2>
    <input type="number" v-model="id"/>

    <!-- Eventually we need a check to confirm it's an ID that exists -->
    <button v-on:click="this.$router.push( {path: `/plantdetail/${id}`} )">Search</button>
</template>

<script>
    export default { 
        data() {
            return {id: ''};
        }
    };
</script>