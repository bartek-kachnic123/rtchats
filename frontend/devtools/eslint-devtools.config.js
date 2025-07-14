import js from '@eslint/js';
import prettier from 'eslint-config-prettier';
import globals from 'globals';

export const eslintDevtoolsConfig = {
  files: ['frontend/devtools/**/*.js'],
  extends: [js.configs.recommended, prettier],

  languageOptions: {
    globals: globals.node,
  },
};
