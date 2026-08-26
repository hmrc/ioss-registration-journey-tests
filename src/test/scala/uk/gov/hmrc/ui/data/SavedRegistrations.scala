/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.data

import java.time.LocalDate

object SavedRegistrations {
  val yesterday = LocalDate.now().minusDays(1)

  val data: List[String] =
    List(
      s"""
         |{
         |  "_id": {
         |    "$$oid": "6a8d9ec575a5d19a85b1d558"
         |  },
         |  "vrn": "600000001",
         |  "data": "N8EHwqlkSR4vjkvAVDIrJG97skj0+U8kelpwkyDq4MDFLDhaEmU7VpoZ+HXwOjaPkq5GT4ol4VFGqFrKO8z1zMVCzLOI8w3gpgcXo/BumYNKOUJfUZc4R+wdajs4LSMSPjuOucCtNZuu4PkwE5sKJ23kqDkCoDO/8lZ+7j8pvX5oMA6c+pg8XnWiSbWGHeAP/AP+XIgFeJ1j/q3bunoVAekG686+N3xmYgEn11ng1SREzYvP12ggnXrVfsmMD4Aog28gPdVAip2x8+M+VKoeT/SBVfYp9Y8iYoOTB+2S1rW9W0O2Wl7D8C6X5lw+YLwqJQCaR/RLr3coB3oHUjriYSzsRL+ZRP5dJBKIMya0DAEEFoTLamXiZ7r23P8oA4+KF9RFWoto0QrFTVmWPADjGVzNJ1CYCw6LQoQGV99LCSde3Ay6m0qBa+H3/yzpAzK7xQS42rKcouOZePQ2EiZA5ymeDccnmEYCnRkKSOIain97rEibwCM7G34QrlZhDay+KqvpRiyDvjUsU8cVOWr9VSigj7aaxAQAdR/TganNL2a60HG5DbyiHGevP4/6kzj/yDX+FI0DhjtVrOq0VJuJkfodXm6fCfI1mLf5Pp3uWygLWdGSqWvzbVRxhdIN01RNn0f07CTzOrKJheWpOc8Ob5fS0MXskVU6kPZk6NhKb2XeGZbuLtptJMVWZvXVkL9iCEhaa+a3Fl6K7j7yGaXK3OR7u0DDEiAC3jRu1ixhgYgxV8J9bvad6tdIFdGXduK/13MTAc7mNhmjwwcpIKJ+e95/TX0YF3KIhKeeXQEVmgKq3HynTQThs+uVFvpgHDsB8MYwY3I0PAa1QZi61vBO8j8hTWzWjIVYmM1iF1qq3rULQolYH96JZGaxMngOpl3cHIYsVv3ub9pYpclzabWhDNlJa68aoZlfnmE0gi1YJoJfPoe04L8W7EA/dGO8",
         |  "lastUpdated": {
         |    "$$date": "${yesterday}T13:55:17.546Z"
         |  }
         |}
      """.stripMargin,
      s"""
         |{
         |  "_id": {
         |    "$$oid": "6a8ea39a75a5d19a85d97549"
         |  },
         |  "vrn": "333333333",
         |  "data": "I2p/nNw2xwxL+YibcIYRT3ZpQ5q6xaAHptiXHtgT6IYezV1Tvz+vokwG4q+apBWilWJBiHyKciy2e7maj/+ijH/HS8TOJE6UnG9w453AhM+nLgZr2qToH1jk5tEcoWPZhC1uD81aix14hLNyH91mldPHkdNh4b+ZnwelrE9KNGmWnZnTMCowi2JnR7N6T4pnfhbU438+2RCdLCuReesnr2A+iYDOA1h8jQzrWigZQc6IFt0sbbS5bBfU9sSWIeNkyCe8ReW8EIL5k56smxV7sHySgdgs/xYu/ZvWmNJVlrTKiHV861/ZbGccM5kKf6r2L6OVPlUXgQjfvvC3lPdJCKIoY6I3XiTI2z/ulwX0mbpM5spR02wfuUOnMiWQUqCOAGgqTDiUowZeW0144yyxbnpic5O4dAFOvQNobfT3N0O2F/NK6dit4/1wGz3tZClsWPUVQNlsfX3HMt06+acTyX49rnKQ4ckB7W3jyjY2do8CluRZVuQssbNLBMnNnMty6C9m8gQHy8C6kDIeJb0UC8snKzmKNQ==",
         |  "lastUpdated": {
         |    "$$date": "${yesterday}T08:28:10.886Z"
         |  }
         |}
      """.stripMargin,
      s"""
         |{
         |  "_id": {
         |    "$$oid": "6a8ec1ff75a5d19a85f881bc"
         |  },
         |  "vrn": "333333334",
         |  "data": "T5ct/skuulYuSJvcYuIZ21G9zLvYw7feQykZEKlxB4LkI6eutMEHfF2cqDRQkgtAmeLBG08K8LT7jmmx33yvgkRzu1lC+ybNjsJXWBWYR4hgl0v4z++znKHJrrei1i2Hz0bkh2WX84HglMi850IBExnkTdq/d0EicHUtqfPqALMswm5YEs89zM9qfdyDkvAqcBqPccETTP0LAZzYC/N0KHgYO6Lqdm4pqmVswfrQFhCqXCNKZk9Wn6u9BgzsOVCRrEKJn4CwquktzI2okJMGjw2jObH/zxLKy1A+6+AB9FzyWVpoS/6ecay3m5Hmd5sr03VCnMMSwW5J+bgfMOY4nEiavi7BrMFpx7I0ByzvQ/A4DRFBZLl1+oWq0OQtHadwzuCaJTU+DpORs1SN685y53DD9q3SwJtsWm8GhcM5wodBXk4lG5XmMLQOiNbEtQGZMMDcKlibCnOoNi8+4DDxItG/Rp83CpvX8xee+6C6BackrFs5bQdoYAAsM82Q",
         |  "lastUpdated": {
         |    "$$date": "${yesterday}T10:37:51.227Z"
         |  }
         |}
      """.stripMargin
    )
}
