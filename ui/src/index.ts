import './shared/ArrayExtensions'
import './global.css'
import 'highlight.js/styles/github.css'
import api from 'src/api/api'
import {initErrorHandlers} from './errorHandlers'
import App from './App.svelte'
import {initSession} from 'src/stores/auth'
import type {User} from 'src/api/types'
import {marked} from "marked";
import {markedHighlight} from "marked-highlight";
import hljs from "highlight.js";

marked.use(markedHighlight({
  langPrefix: 'hljs language-',
  highlight(code, lang) {
    const language = hljs.getLanguage(lang) ? lang : 'plaintext';
    return hljs.highlight(code, { language }).value;
  }
}), {mangle: false, headerPrefix: 'heading-'});

(async function() {
  initErrorHandlers()

  const [auth] = await Promise.all([api.get<User>('user').catch(() => null)])
  if (auth) initSession(auth)

  document.body.innerHTML = ''
  new App({target: document.body})
}())
