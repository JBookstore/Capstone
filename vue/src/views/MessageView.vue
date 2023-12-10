<template>
    <div class="card">
        <h3 id="sender">{{ message.from_user_id }}</h3>
        <h3 id="dateTime">{{ message.date_and_time }}</h3>
        <div id="messageBody">
            <p>{{ message.message_body }}</p>
        </div>
        
    </div>
</template>

<script>
export default {
   
    data() {
        return {
            message: {}
        }
    },

    computed: {},

    created() {
        this.message = this.$store.state.activeMessage;
    }
}
</script>

<style scoped>
.card {
    display: flex;
    flex-wrap: wrap;
    width: 100vw;
    height: 80vh;
    background-color: aqua;
    text-align: center;
}

#sender{
    text-align: start;
    width: 50vw;
    height: 20px;
    background-color: white;
}

#dateTime {
    text-align: end;
    width: 50vw;
    height: 20px;
    background-color: blue;
}

#messageBody {
    position: relative;
    width: 100vw;
    height: 80vh;
    background-color: gold;
}

#messageBody > p {
    text-align: start;
}
</style>