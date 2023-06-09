export enum CountryCode {EE = 'EE', LV = 'LV', LT = 'LT'}
export enum Role {ADMIN = 'ADMIN', SUPPORT = 'SUPPORT', TRAINER = 'TRAINER', CUSTOMER = 'CUSTOMER'}
export interface User {email: string; firstName: string; lastName: string; lang: string; phone?: string; personalCode?: string; role: Role; avatarUrl?: string; id: string;}
export interface AuthenticatedUser {user: User}
export interface Address {county: string; municipality: string; area?: string; street: string; building: string; apartment?: string; postalCode: string; details?: string; countryCode: CountryCode; id: string;}
export interface Post {
    title: string
    subheadline: string
    slug: string
    content: string
    date: string
    createdAt: string
    updatedAt?: string
    id: string
    userId: string
}

export interface PostInList {
    details: Post,
    author: { userId: string, name: string }
}
export interface Tag {id: string, color: string}
export interface PostTagCount {tag: Tag, count: number}
