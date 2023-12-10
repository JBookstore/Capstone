<template>
        <div class="forumCard">
        <div class="cardTop">
            <h1>12/10/23 9:00PM</h1>

            <h2>user says -</h2>

            <p>
                We'll be limiting these posts to 300 characters or whatever, so it probably wouldn't be too tall and ask to just
                list the entire content of the post here, and bind all the relevant data to this object at the time it's created.
            </p>

            <img class="cardImage" src="../assets/garden.jpg" />

            <h3>No. of Replies: X</h3>
            
            <div class="buttons">
                <button v-on:click="onAddClick" class="cardButton">VIEW THREAD</button>
                <button v-on:click="onDeleteClick" class="cardButton">REPLY</button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    
}
</script>

<style scoped>
.forumCard {
  text-align: left;
  border: 2px solid black;
  background-color: rgb(245, 237, 237);
  border-radius: 10px;
  width: 70vw;
  height: auto;
  margin: 20px;
  padding: 10px;
}

.cardImage {
  margin-top: 5px;
  width: 70vw;
  height: auto;
}

.cardTop {
    height: auto;
}

.cardTop > h1 {
    font-size: 70%;
    color: darkgreen;
}
.cardButton {
    width: 40vw;
}

.buttons {
    display: flex;
}

</style>