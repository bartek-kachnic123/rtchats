#!/usr/bin/env node
import { spawn } from 'child_process';

import config from '../devtools.config.js';

function main() {
  const [, , command, ...args] = process.argv;
  executeCommand(command, args);
}

function executeCommand(command, args) {
  const commandConfig = config[command];

  if (!commandConfig) {
    console.error(`Unknown command: ${command}`);
    showHelp();
    process.exit(1);
  }

  const [cmd, ...defaultArgs] = commandConfig;
  run(cmd, [...defaultArgs, ...args]);
}

function run(cmd, args) {
  const proc = spawn(cmd, args, { stdio: 'inherit' });
  proc.on('exit', (code) => process.exit(code));
}

function showHelp() {
  console.log('Available commands:');
  Object.keys(config).forEach((cmd) => {
    console.log(`  - ${cmd}`);
  });
}

main();
