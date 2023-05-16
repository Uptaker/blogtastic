import type {Dict, Options} from '@codeborne/i18n-json'
import {_, formatAmount, formatUuid, init, lang, rememberLang} from '@codeborne/i18n-json'
import {navigate as navigatorNavigate} from 'svelte-navigator'
import type {NavigateOptions} from 'svelte-navigator/types/history'

let date = new Date()
export const today = date.toLocaleDateString('lt')

date.setDate(date.getDate() - 1)
export const yesterday = date.toLocaleDateString('lt')

export const formatId = formatUuid

