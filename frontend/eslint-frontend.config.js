import js from '@eslint/js';
import prettier from 'eslint-config-prettier';
import reactHooks from 'eslint-plugin-react-hooks';
import reactRefresh from 'eslint-plugin-react-refresh';
import globals from 'globals';

export const frontendEslintConfig = {
  files: ['frontend/**/*.js', 'frontend/**/*.jsx'],
  extends: [
    js.configs.recommended,
    reactHooks.configs['recommended-latest'],
    reactRefresh.configs.vite,
    prettier,
  ],
  languageOptions: {
    globals: globals.browser,
    parserOptions: {
      ecmaFeatures: { jsx: true },
    },
  },
};

export const frontendIgnoredFiles = {
  ignores: ['frontend/coverage/**', 'frontend/dist/**'],
};
