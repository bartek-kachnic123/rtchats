import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import { ValidateEnv } from '@julr/vite-plugin-validate-env';
import { validateEnvConfig } from './validate-env-config.js';

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(), ValidateEnv(validateEnvConfig)],
});
