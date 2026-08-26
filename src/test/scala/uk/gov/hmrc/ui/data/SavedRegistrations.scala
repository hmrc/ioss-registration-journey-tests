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
      """.stripMargin,
      s"""
         |{
         |  "_id": {
         |    "$$oid": "6a8eddd375a5d19a8507a2e2"
         |  },
         |  "vrn": "100000100",
         |  "data": "MhvfST/n56pM15kEHZ1vZX1y1Ldv1jXjmiVJ+mBXyU43QxQ6JaTz0RGXAScUDHpx7c6zVMiv0GLjzlol6yTwRBd6H1/Z9FelzyJHA+9eY11MtycmrEK0msk2/HtaeZFXMfFZW3S1VIz2sokXbPOUArj4XX0WISjFJVRIJ9pV7DaRpcAa2/lJ/xfRg+MuaRjuwEbAuhzdYJkswdMFLerSKHziZb1nROFqNemgsWMx7rct19+XV9IN7+yKBjd2Hv6rz36cJKjXtRAh6N63faD6fRiaV7TFeE7Z4Qp6kAiJXB2GOsiiG/Gr9hPWDc+XKijKXFcPaXmIuXiYhd5Q6xsMemxMMnqwhOqq2W5w/McOd4PyM6ywUIuvGRwdZwKQrE04u9XWp3M4y4VtSSw7wPRUam0VP0nJJFTjA/mLtNXucDpEV9T9r/8D1utWsyODGF2MFzM5Ay0LM7PrdphhuajbDspD3MDAZR2ekGUR3hq7L/XO1l6vSp0ZJAZa/URa/uPAOx+EuVTQCk4hHhShQsEun8e+p/Wx0J+a729ka6WBkX8zRG8/lP0RqsqhnFfiYxL7R5gxPFdtaAzBj3vAvwo3Ewt5qiZdUSg7U61mX/sUFAszgFk3EgK/aO26+Lf0c89zZnY29R5UMBVh8XzyXZLvnzHw8UCPNaxvhf+kCYZkLCw/daVjFuyAB6vJsoNCysPQtRqsEpXcTuMB50TEFoEQ8EzuWpzmfOJXtphqgmxIl1e4SmQwmXtOzeG2w9gt5wfAoGDkjDJ1uOukcecoPB5vfTG8Z2ThoGoGdluyCkokdi+NeiNXUvTJnvKCbg/0XpQ922is7dmjl6+yMeF7qushpiezKI3de/JDPQsYMl5OppBPSI3iMVYXHzj8",
         |  "lastUpdated": {
         |    "$$date": "${yesterday}T12:36:34.996Z"
         |  }
         |}
      """.stripMargin,
      s"""
         |{
         |  "_id": {
         |    "$$oid": "6a8ef23c75a5d19a851c8396"
         |  },
         |  "vrn": "100000200",
         |  "data": "Awew2Mcg1ABPdgs5BJkrAHUuBkf/w06NPYFnQ3WZDo89ZNDBIuoI8j1Cysw0q3naBaA0zMz01srFr5XEtMpM6vDW5Fy2GQXEspnMXOFGQBpWunDAWoRgYlHXVdY7nevYhxeYdW/xEIDNsr6PwoXFFz5lqtmLU0k76dL9tiCmdmcUy0dwo8xS+h+wUAJ66rtRYOBsUb+gnoWKrUlpROD+TyjFjapoBHinu1ywBiuoRzJrOjYyQ9bHDFwk+lar3O5sSUs7OvbfmMZQnazamn8+eGC2YtqzQwduAUhOejoifjQH4I3jxttp1WvNwAdmup3vJ86/W6mkSDJfLu6HOlA3i3Xjbikch8DGqbW2EJSkipg8CLvC/7yd37SKPtT5TfHSxyC4qGQvaZ1DE6ZNPqxCuz8iSl7PVrVCWnPGs13ayAGLsLRnqi+BygXLghYqLh4wXPdV92TkXuRDq1denameGHOVVtqw3XsYNo4oYJgrRuEr3yDmRQ34UspCZEKq8+Kwcroh8XR43YtUy4+y02enWMW1u9BP1r2uN09FYs1W8faA17hlpYO5EkPFMrWDrU0z6yupQZZSbo0a5Sl7WFljeoi+bS/TSOpkAh72IQoTIBk6Ux04vpIg4kZj5/eY/xWw/33PKTqZ+mE3Q8YVUOtc7wBrCxRQTiIdI1nV3fnDwAr/Cic5rcflDMTi5/8mUkSa9B9HJHIT3CvrHGLaxz8czdCyKu0F+Z1xXrwUz5vjL2XueFzz1ZfO+waXYYhgZ/18rsi+E2JYUF4pHv7qNqNvFHKwxAFeRdYKWBFZ1TpYp1m2JmAWIAGlQrIFaZQeAlSew7B5ltcG4AGCTNMVWXEH3FQ0Qp85ycuxKI7rzpoPiSns5l59lJDF8Q==",
         |  "lastUpdated": {
         |    "$$date": "${yesterday}T14:03:40.117Z"
         |  }
         |}
      """.stripMargin,
      s"""
         |{
         |  "_id": {
         |    "$$oid": "6a8ef30c75a5d19a851d58f1"
         |  },
         |  "vrn": "100000300",
         |  "data": "cPLQSuQsXexxXFA+Tjmst/jo41SK9r+G4TfukTYeFZbBs2Gq4beaw2uQVykC9wGbKicHcGw+l3f2FfCSIHwWj0n1ulNSvSX6Bmo64fQ1BFXyUYwZl9iBm62aAI+67Y3t+92/XLBncLww/CdVGsdLHlG3WV3BGIXcyBK1vJAUl/IhVek7OtppybOZHv7y+tHA17h6suPgrBagrBk0Vk6tySGEl/io1wxceja/KdxUsHSV8eTqvsYjKLDI8pGPENunnHh0gI52gTsOXAeuqWS2r2Tce8FcFvgt8Zt2SkcDU5er2ek9h9OmcF/rhQVzNgs9qQ+PvrPm7Q/1I1MqDZSiVAILU0YP13RigbArCGfJBoovlugKgh25vpLenfuezkGpQJ3FHlWjDSfmtBMolYYYTPPyYAYkSvd+g6u0Q6/S384rxwW4yyjvz7+oB0Q84hsFDKqK02EH7gl4+vbLnInPDSei8wBqZXDl0J8RR4f0QCgMKEC2ZQl76Vv2kj/pmwhWK+Xm0Jhik5Y7wzd7xyvfgzOyUwLsXbwv+joJl/JLkxvCXJwzFiQ75jMEM04kPIPK7ejyA4fAghVAtw4nn2lWap+ENukq8K1r28k1NjclpPfFznwIE59WOYDmkw+Ev9MFIbexRPg23dDl4oMtLwLxuqagbXTR/NR7IzTZLzVWtJc2HrTrZ9TCiZJIp57/XlQ/D3P1j7LZHMW+dWGwS3zRgXaP",
         |  "lastUpdated": {
         |    "$$date": "${yesterday}T14:07:07.952Z"
         |  }
         |}
      """.stripMargin,
      s"""
         |{
         |  "_id": {
         |    "$$oid": "6a8ef37675a5d19a851dc670"
         |  },
         |  "vrn": "100000400",
         |  "data": "5g9wgVd3jinwxAT+mcswGE9xXHQSUO+1XaQlFCMEPAeE+5226IEMr9lnaggugR9d2cuHsNhgh0hn6UR9qFqi0+Yt+rIWdKQ4s/evGOCu7nMm9vXyEyvd68i2zCLUa9bJJKoAVHDlNWX0jIo2+FBzLd0/JFQjymWal9uNOlx/PZwChuqRioPIs1v7jxD8YmGWioxmag665A+W64gp7JD68sQ+ykCWVk9qcpEpRI6rARcsr38pSjnB/lVYcXzE4k7mYYlgrVwiUKF2ydTTlLzQiP3mAgzoQeRn9u8Emsawb62lnrjcdC0aw2Vv1l6cfnD/OHfyDh1NpPFvpg8fjKtnjbi7by05+DFKuZSbo1kE3OWHIthpyH814lJLVlYzqRR7FcM71elwhkaMjH6dyNr3yectNgRRPp03rusgmRPEkpw0nOIV5zIcZbnJt0cOFl6/tCQg7M2THprBH5wNyG716JJ0BoUpjnOW2SOfX21sW0cAUV6KmsDBQv1Ogzbc43/w4dDwyzMTEacIGdN1RoX5HR5tqutVrcy7edwYfp4pbL4KacXqlRxv/OQSkav4RCEwdkd9Pb14wVeQsbntcwUK+H6NbNlIlQL5uhmo++yHTW2W959KvkV027Uvq17MWUYbtUIr82cg7tHR+NJ+DOJZtoH+VfP85GQ4/D62pDdSpYjq6510RqdEvMlpPJiDT/hSljRayZwipSSrkapQ1HMJx9oALs24xcc48HpVv4OHG+YqeefZmNueeZNC6ZZYgLZBqE4swdcnC9LosAQ486CMmoq/YyFlfFvi",
         |  "lastUpdated": {
         |    "$$date": "${yesterday}T14:08:54.525Z"
         |  }
         |}
      """.stripMargin,
      s"""
         |{
         |  "_id": {
         |    "$$oid": "6a8ef40a75a5d19a851e5e85"
         |  },
         |  "vrn": "100000500",
         |  "data": "E76a8Rn7Vu39bZFZU8XgIjebCKf9YLCH+4j5OXzPXkdZlVu6XQObwM6eIlf+TnQrsA/Uzp3KUcLO1rKR6xR9wBCCDRIkji2yOSv+OUmxss15rw2SpUYXugud8UMPrPLtGU98Hx1eg0Hd/iyATWnvSGQ2+pTEp1JO5ZvXEylO+s28jeCCFLEy6j0I/sOEXnv33TbTdL+H2tJsLw/Tfe0g/il/2IHH06BlyK+N7JqKT4Me/etiO8ysW2AXbUqGO8hPKR55w1MquVULEkVKzXQdJll9J3EQQmh+GDhd00bCPoBPcV2OYrOTXMjqiqHN9IR6z2DKIzOYFI/JPNUAscyu1k7rGw8jS05Jaz6OH0RrlsGx/SGSZyGaMKxpSiYnMLSbS1HdsKM1btuIkEtfU15jM8IX1V8K81YKer6DUEoIdJxOWnTtrQKeuhXtxfEoA00TSkkEPfpz+mksOSk87138yOTGfNy9CCU1B4Ciwd3ZOWfZt0+q08+DG5GUQJgvkfk0V4i1pP76wNlFd6TEU8baIj5vQMCVfaxBaPfw3rSkMsJHnLXTYoh9+4DLTL6oSWqrUHoTobqO/RbqVJS+s5lj0+LuaeCgVPU/bNgIbW5/9NWjNp/RCQRvf7MxCdF96e2yHzD8Kp1yU7uFVWWWWstlkJjVq5bBDNToHYjlIamPmmijvQk15/5jdPp+a3swkCUlMlC6jfW2gcsn8NM/c/qYDu8RL9/0Ku2J2hrU+NaeHmyU/Ryfpi5Jnnl4vJJ1RMOYdKs6xYjDZSuteb5u2XGsqynHXAOh8KZn0TGuJpve7QJWuSlYSCkZ7VXd2yFabLdcBH5b3RZjv2x4L4HKdYZ67snFCNOQL6uWxdR4kFLJ8+ZXzIb3ZjiK10puytrBoKIei2EqnmUEs8f1YNG15tPBNPsrZyD6degyNKBA",
         |  "lastUpdated": {
         |    "$$date": "${yesterday}T14:11:22.129Z"
         |  }
         |}
      """.stripMargin,
      s"""
         |{
         |  "_id": {
         |    "$$oid": "6a8ef49975a5d19a851ef166"
         |  },
         |  "vrn": "100000600",
         |  "data": "oMS+JaUJJzegnAfL7U83ziXhlOPZT2wIDO6BIsOhtna3B+biXpdFb1tLnsZpd0p7Mz8uPfrdqs49q2KeOPjlEhghqxyOjjKsGoX6XatiwgxYTE44j6+EE2HHrKtuFzpD4OroLQh5kPZtYi0iveFfu7yH/OTQ6uqEI85daDPseuPpT/43Rcy58lfNb1TjrVOy721QnntZetQpoQirr/Kv3S3oG/PGS+5gbMjGvAFAuVPohgI9uJgpgIAgKZjkOHzfutQGkq4cSRO7nCciOCEpZkwt6TIInik7YI1qsRgwtsLXpCg+3x9nvnsa7abLn5r9R5Ae1LigVdz0XXHVFNzhBbwN7+vNEzkUfjwCeowcHHSrYYX/ejCCv0pcekTUh1Eny4Wy3EWJ6TIXepDTUQUbwHxKMPdQec4FRtiA0NtwdZv12KTAyyi/uthjlO7o4Odtjq7qgR2YPm1kwK1gG2DznqAbd7DIzIvWeI7XyjVFiU1DQ3lyOjqtR6B3ZSiSJcsRZ/QaeqBwGmsXOZ8C5VD5uPbJrTB7TeRCRx4L04hko4r2+JB/q3uhPOflwxeE/uU/PYPFSMyhRMEzt4yS1kl50f7uQf4XiFhdkBW6r6UgdGY9Imy48xp826Uykju7KjNXLoLEU9lj+3uDUNl6YEBDQsvG833m9j0MnQUIKomNnvJhZauET6mCxYxCnXyCBltuc5KC5AE4XFftB58CWKQ3FQ==",
         |  "lastUpdated": {
         |    "$$date": "${yesterday}T14:13:45.200Z"
         |  }
         |}
      """.stripMargin
    )
}
