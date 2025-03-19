<script setup>
// v-model:activeSection을 사용하기 위한 props와 emit 설정
const props = defineProps({
  activeSection: {
    type: String,
    required: true,
    validator: (value) => ['quickOptions', 'textInput'].includes(value)
  }
});

const emit = defineEmits(['update:activeSection']);

const selectSection = (section) => {
  emit('update:activeSection', section);
};
</script>

<template>
  <div class="grid grid-cols-2 w-full max-w-md mx-auto bg-neutral-800 rounded-lg p-1">
    <button
      @click="selectSection('quickOptions')"
      class="flex items-center justify-center px-4 py-2 rounded-md"
      :class="activeSection === 'quickOptions' ? 'bg-neutral-700 text-white' : 'text-neutral-400 hover:text-white'"
    >
      <svg class="w-4 h-4 mr-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
        stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
        <path d="M22 12h-4l-3 9L9 3l-3 9H2" />
      </svg>
      Quick Options
    </button>

    <button
      @click="selectSection('textInput')"
      class="flex items-center justify-center px-4 py-2 rounded-md"
      :class="activeSection === 'textInput' ? 'bg-neutral-700 text-white' : 'text-neutral-400 hover:text-white'"
    >
      <svg class="w-4 h-4 mr-2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none"
        stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
        <path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z" />
      </svg>
      Tell Your Story
    </button>
  </div>
</template>