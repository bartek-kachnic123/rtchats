import { ValidateEnv } from '@julr/vite-plugin-validate-env';
import react from '@vitejs/plugin-react';
import { defineConfig } from 'vite';

import { validateEnvConfig } from './config/validate-env.config.js';
import vitestConfig from './config/vitest.config.js';

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(), ValidateEnv(validateEnvConfig)],
  vitestConfig,
});
