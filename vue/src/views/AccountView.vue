<template>
    <div>
    <div class="home">
      <p>How does your garden grow?</p>
      <button v-on:click="createGarden">UP</button>
    </div>

    <form v-on:submit.prevent="submitForm" class="cardForm" v-if="showForm">
        <hr>
        <fieldset class="mandatory">
            <label for="title">Title:</label>
            <input type="text" />
        </fieldset>            

        <fieldset class="optional">
            <label for="address">Address:</label>
            <input type="text" />
        </fieldset>    
        <fieldset class="optional">
            <label for="city">City:</label>
            <input type="text" />
        </fieldset>   
        <fieldset class="optional">
            <label for="state">State:</label>
            <input type="text" />
        </fieldset>   
        <fieldset class="optional">
            <label for="zip">ZIP:</label>
            <input type="text" />
        </fieldset>   
        <fieldset class="optional">
            <label for="description">Description:</label>
            <input type="text" />
        </fieldset> 
        <fieldset class="optional">
            <label for="phoneNumber">Phone Number:</label>
            <input type="text" />
        </fieldset> 

        <fieldset class="mandatory">
            <label for="isPublic">Public?</label>
            <input type="text" />
        </fieldset> 
        <fieldset class="mandatory">
            <label for="gardenType">Garden Type:</label>
            <input type="text" />
        </fieldset> 

        <button v-on:click="clearForm">CLEAR</button>
        <button v-on:click="submitGarden">SUBMIT</button>
    </form>

</div>
</template>

  <!-- OPEN ACCOUNT -->

  <!-- MY GARDENS -->

  <!-- MY MARKETPLACE -->

  <!-- [SET] REMINDERS -->

  <!-- [SET] EVENTS -->

  <!-- VOLUNTEER -->
  
<script>
  import plantService from '../services/PlantService.js';

  export default {
    data() {
        return {
            showForm: false,
            garden: {}
        }
    },

    methods: {
        createGarden() {
            this.showForm = true;
            alert('This button works!!');
 
            // this.showForm = false;
        },
        clearForm() {
            // alert('Cleared');
            this.showForm = false;
            this.garden = {};
        },
        submitForm() {
            alert('Submitted')
            this.showForm = false;
            this.garden = {};
        }
    }
  };
</script>

<style scoped>
div {
    text-align: center;
}

fieldset {
    text-align: center;
}

button {
    width: 20vw;
}

.cardForm {
    width: 80vw;
}

.mandatory {
    background-color: rgb(241, 227, 227);
}
</style>
  