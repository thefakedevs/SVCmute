# BucketMute

**Voice chat moderation for Minecraft servers**

BucketMute is an add-on for [Simple Voice Chat](https://modrinth.com/plugin/simple-voice-chat) that allows administrators to temporarily or permanently mute players' microphones.

![Minecraft 1.20.1](https://img.shields.io/badge/Minecraft-1.20.1-green)
![Velocity](https://img.shields.io/badge/Velocity-3.3.0-blue)
![Forge](https://img.shields.io/badge/Forge-47.x-orange)

---

## 📖 Description

BucketMute solves the problem of voice chat moderation on servers. When a regular text mute is not enough, this plugin allows you to completely block a violator's microphone.

### Features

- **Temporary and permanent mute** — specify a duration or mute forever
- **Client indication** — muted players see a special icon
- **Multilingual** — messages in Russian and English
- **LuckPerms integration** — flexible permission configuration

---

## 📦 Installation

### Requirements

**Server (Velocity):**
- Velocity 3.3.0+
- [Simple Voice Chat](https://modrinth.com/plugin/simple-voice-chat)
- [LuckPerms](https://luckperms.net/)

**Client (Optional):**
- Forge 47.x for Minecraft 1.20.1
- [Simple Voice Chat](https://modrinth.com/mod/simple-voice-chat)

### Server Side

Place `bucketmute-velocity.jar` in the `plugins` folder of your Velocity server.

### Client Side

> The client mod is optional. Without it, the mute works, but the player won't see the special icon.

Place `bucketmute-forge.jar` in the `mods` folder.

---

## 💻 Usage

### Commands

| Command | Description |
|---------|-------------|
| `/bucketmute <player> [time]` | Mute a player |
| `/bucketunmute <player>` | Unmute a player |
| `/bucketmutelist` | List muted players |

### Time Format

| Suffix | Meaning |
|--------|---------|
| `s` | Seconds |
| `m` | Minutes |
| `h` | Hours |
| `d` | Days |

**Examples:**
```
/bucketmute Player123 30m     — mute for 30 minutes
/bucketmute Player123 2h      — mute for 2 hours
/bucketmute Player123         — permanent mute
```

### Permissions

| Permission | Description |
|------------|-------------|
| `bucketmute.mute` | Use `/bucketmute` |
| `bucketmute.unmute` | Use `/bucketunmute` |
| `bucketmute.list` | Use `/bucketmutelist` |

---
---

# BucketMute

**Модерация голосового чата для Minecraft серверов**

BucketMute — это дополнение к [Simple Voice Chat](https://modrinth.com/plugin/simple-voice-chat), которое даёт администраторам возможность временно или навсегда отключать микрофон игрокам.

![Minecraft 1.20.1](https://img.shields.io/badge/Minecraft-1.20.1-green)
![Velocity](https://img.shields.io/badge/Velocity-3.3.0-blue)
![Forge](https://img.shields.io/badge/Forge-47.x-orange)

---

## 📖 Описание

BucketMute решает проблему модерации голосового чата на серверах. Когда обычного текстового мута недостаточно — этот плагин позволяет полностью заблокировать микрофон нарушителю.

### Особенности

- **Временный и перманентный мут** — укажите время или оставьте навсегда
- **Клиентская индикация** — замьюченный игрок видит специальную иконку
- **Мультиязычность** — сообщения на русском и английском
- **Интеграция с LuckPerms** — гибкая настройка прав доступа

---

## 📦 Установка

### Требования

**Сервер (Velocity):**
- Velocity 3.3.0+
- [Simple Voice Chat](https://modrinth.com/plugin/simple-voice-chat)
- [LuckPerms](https://luckperms.net/)

**Клиент (опционально):**
- Forge 47.x для Minecraft 1.20.1
- [Simple Voice Chat](https://modrinth.com/mod/simple-voice-chat)

### Серверная часть

Поместите `bucketmute-velocity.jar` в папку `plugins` вашего Velocity-сервера.

### Клиентская часть

> Клиентский мод опционален. Без него мут работает, но игрок не увидит специальную иконку.

Поместите `bucketmute-forge.jar` в папку `mods`.

---

## 💻 Использование

### Команды

| Команда | Описание |
|---------|----------|
| `/bucketmute <игрок> [время]` | Замутить игрока |
| `/bucketunmute <игрок>` | Снять мут |
| `/bucketmutelist` | Список замьюченных игроков |

### Формат времени

| Суффикс | Значение |
|---------|----------|
| `s` | Секунды |
| `m` | Минуты |
| `h` | Часы |
| `d` | Дни |

**Примеры:**
```
/bucketmute Player123 30m     — мут на 30 минут
/bucketmute Player123 2h      — мут на 2 часа
/bucketmute Player123         — перманентный мут
```

### Права доступа

| Право | Описание |
|-------|----------|
| `bucketmute.mute` | Использование `/bucketmute` |
| `bucketmute.unmute` | Использование `/bucketunmute` |
| `bucketmute.list` | Использование `/bucketmutelist` |

---